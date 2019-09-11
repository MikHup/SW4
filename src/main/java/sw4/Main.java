package sw4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sw4.ast.*;
import sw4.ast.BuildAstVisitor;
import sw4.ast.nodes.ProgNode;
import sw4.errorhandling.SemanticErrorHandler;
import sw4.errorhandling.SyntaxErrorHandler;
import sw4.errorhandling.SyntaxErrorListener;
import sw4.gen.jamlLexer;
import sw4.gen.jamlParser;
import sw4.symboltable.SymbolTable;

import javax.sound.midi.MidiSystem;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isRunable;
        boolean playFile;
        boolean prettyPrint;
        boolean createMidiFile;
        String fileName = null;
        Scanner inputReader = new Scanner(System.in);

        System.out.println("-- Welcome to the Jaml v0.999 compiler. Type 'help' for instructions --");
        System.out.println();

        while (true) {
            playFile = true;
            prettyPrint = false;
            createMidiFile = false;
            isRunable = false;

            System.out.println("Input command: ");
            String consoleInput = inputReader.nextLine();

            if(consoleInput.equals("help")){
                System.out.println("Help:");
                System.out.println("To compile a input file, type: 'jaml' followed by the filepath to a jaml file");
                System.out.println("Example: 'jaml C:/folder/filename.jaml'");
                System.out.println();
                System.out.println("The default behavior of the compiler is to play back the given input, without generating a midifile to disk");
                System.out.println("To change this behavior, make use of the following compiler options");
                System.out.println();
                System.out.println("Compiler options:");
                System.out.println("    -stop");
                System.out.println("    -midi");
                System.out.println("    -print");
                System.out.println();
                System.out.println("The '-stop' option cancels the playback of the input");
                System.out.println("The '-midi' option generates a midi file at the same location of the input file");
                System.out.println("The '-print' option print the ast nodes generated from the input file");
                System.out.println();
                System.out.println("Example: 'jaml C:/folder/filename.jaml -stop -midi -print'");
                System.out.println();
                System.out.println("To stop the program type 'exit' or 'quit'");
                System.out.println();
            }
            else if(consoleInput.toLowerCase().equals("quit") || consoleInput.toLowerCase().toLowerCase().equals("exit")){
                System.exit(0);
            }
            else if(consoleInput.toLowerCase().startsWith("jaml")){
                consoleInput = consoleInput.replace("\\", "\\\\");
                String inputArray[] = consoleInput.split(" ");

                if(FilesUtil.isFilenameValid(inputArray[1])){
                    fileName = inputArray[1];
                    isRunable = true;
                }
                else{
                    System.err.println("Filepath: '" + inputArray[1] + "' is invalid");
                    System.err.println("Type 'help' for instructions");
                }

                for(int i = 0; i < inputArray.length - 2; i++)
                    switch (inputArray[i+2].toLowerCase()){
                    case "-midi":
                        createMidiFile = true;
                        break;
                    case "-print":
                        prettyPrint = true;
                        break;
                    case "-stop":
                        playFile = false;
                        break;
                    default:
                        System.err.println("Input command: '" + inputArray[i+2] + "' could not be recognized");
                        System.err.println("Type 'help' for instructions");
                }
            }
            else{
                System.err.println("Input command: '" + consoleInput + "' is invalid");
                System.err.println("Type 'help' for instructions");
            }

            if(isRunable) {
                try {
                    //Create antlr lexer and parser
                    CharStream input = CharStreams.fromFileName(fileName);
                    jamlLexer lexer = new jamlLexer(input);
                    CommonTokenStream tokens = new CommonTokenStream(lexer);
                    jamlParser parser = new jamlParser(tokens);

                    //Remove standard error handler and replace with our own
                    lexer.removeErrorListeners();
                    lexer.addErrorListener(new SyntaxErrorListener());
                    parser.removeErrorListeners();
                    parser.addErrorListener(new SyntaxErrorListener());
                    parser.setErrorHandler(new SyntaxErrorHandler());

                    //Create ast from cst provided by antlr
                    ProgNode ast = (ProgNode) new BuildAstVisitor().visitProg(parser.prog());
                    SemanticErrorHandler semanticErrorHandler = new SemanticErrorHandler();
                    SymbolTable symbolTbl = new SymbolTable();

                    //Semantic checker instance
                    SemanticCheckVisitor scVisitor = new SemanticCheckVisitor(semanticErrorHandler, symbolTbl);

                    if (SyntaxErrorHandler.isErrorFree) {
                        //Semantics check
                        scVisitor.Visit(ast);
                    } else {
                        break;
                    }


                    if (semanticErrorHandler.IsErrorFree()) {
                        if(prettyPrint) {
                            //Pretty print ast
                            PrettyPrintVisitor ppVisitor = new PrettyPrintVisitor();
                            ppVisitor.Visit(ast);
                        }
                    } else {
                        semanticErrorHandler.PrintError();
                        break;
                    }

                    if (semanticErrorHandler.IsErrorFree()) {
                        //Code gen
                        CodeGenVisitor cgVisitor = new CodeGenVisitor(symbolTbl);
                        cgVisitor.Visit(ast);

                        if (semanticErrorHandler.IsErrorFree()) {
                            //Set sequence
                            cgVisitor.midiGen.sequencer.setSequence(cgVisitor.midiGen.sequence);

                            if(playFile) {
                                //Play
                                cgVisitor.midiGen.sequencer.start();

                                //Wait for sequence to finish
                                Thread.sleep(cgVisitor.midiGen.sequencer.getMicrosecondLength() / 700);

                                //Close sequence
                                cgVisitor.midiGen.sequencer.stop();
                                cgVisitor.midiGen.sequencer.close();
                            }
                            if(createMidiFile) {
                                //Generate midi file
                                String filePath = fileName.substring(0, fileName.lastIndexOf(".")).concat(".midi");
                                try {
                                    File midiFile = new File(filePath);
                                    MidiSystem.write(cgVisitor.midiGen.sequence, 1, midiFile);
                                } catch(SecurityException e){
                                    System.err.println("Output file path: " + filePath + " does not have write permission");
                                }
                            }
                        }
                        else {
                            semanticErrorHandler.PrintError();
                            break;
                        }
                    }
                    else {
                        semanticErrorHandler.PrintError();
                        break;
                    }
                } catch (Exception e) {
                        System.err.println("An unexpected error occurred");
                        System.err.println("Type 'help' for instructions");
                }
            }
        }
    }
}