package net.pistonmaster.nativepolyglot;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.SandboxPolicy;

public class Main {
  public static void main(String[] args) {
    try (Context context = Context.newBuilder()
        .sandbox(SandboxPolicy.TRUSTED)
        .allowAllAccess(true)
        .build()) {

      var java = context.getBindings("java");
      var integer = java.getMember("java.lang.Integer").newInstance(4);
      System.out.println(integer);
    }
  }
}