package net.pistonmaster.nativepolyglot;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.SandboxPolicy;

public class Main {
  public static void main(String[] args) {
    //if (true) {
    //  try (Context context = Context.newBuilder()
    //      .sandbox(SandboxPolicy.TRUSTED)
    //      .allowAllAccess(true)
    //      .build()) {
//
    //    var java = context.getBindings("java");
    //    var integer = java.getMember("java.lang.Integer").newInstance(4);
    //    System.out.println(integer);
    //    return;
    //  }
    //}

    if (args.length < 2) {
      throw new IllegalArgumentException("Usage: program_name <jar_file> <main_class>");
    }

    final String jarFilePath = args[0];
    final String mainClassName = args[1];
    final String[] arguments = new String[args.length - 2];
    System.arraycopy(args, 2, arguments, 0, arguments.length);

    try (var context = Context.newBuilder("java")
        .option("java.Classpath", jarFilePath)
        .sandbox(SandboxPolicy.TRUSTED)
        .allowAllAccess(true)
        .build()) {
      var mainClass = context.getBindings("java").getMember(mainClassName);
      if (mainClass == null) {
        throw new IllegalStateException("Failed to load the main class!");
      }

      mainClass.invokeMember("main", (Object) arguments);
    }
  }
}