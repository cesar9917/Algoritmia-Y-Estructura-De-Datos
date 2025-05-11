package ejercicio_3;

import ejercicio_1.StackLink;

public class Application {
public static boolean symbolBalancing(String s) {
StackLink<Character> stack = new StackLink<>();
for (int i = 0; i < s.length(); i++) {
char c = s.charAt(i);
if (c == '(' || c == '[' || c == '{') {
stack.push(c);
} else if (c == ')' || c == ']' || c == '}') {
if (stack.isEmpty()) return false;
try {
char top = stack.pop();
if ((c == ')' && top != '(') ||
(c == ']' && top != '[') ||
(c == '}' && top != '{')) {
return false;
}
} catch (ExceptionIsEmpty e) {
return false;
}
}
}
return stack.isEmpty();
}
public static void main(String[] args) {
String[] tests = {
"()()()[()]()", // true
"((()))[]", // true
"([])[](", // false
"([{)]}", // false
"[", // false
"[][][]{{{}}}" // true
};
for (String test : tests) {
System.out.println(test + " -> " + symbolBalancing(test));
}
}
}
 public static void main(String[] args) {
     System.out.println("\n--- Prueba del Problema de los Corchetes ---");
     System.out.println("\"()()()[()]()\": " + isSymbolBalanced("()()()[()]()")); // true
     System.out.println("\"((()))[]\": " + isSymbolBalanced("((()))[]"));       // true
     System.out.println("\"([])[]((\": " + isSymbolBalanced("([])[](("));     // false
     System.out.println("\"([{)]}\": " + isSymbolBalanced("([{)]}"));       // false
     System.out.println("\"[\": " + isSymbolBalanced("["));                 // false
     System.out.println("\"[][][]{{{}}}\": " + isSymbolBalanced("[][][]{{{}}}")); // true
 }
}
