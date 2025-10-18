package org.algorithm_base.exercise;

import java.util.Stack;

/**
 * 给定一个包含三种括号（ ()、[]、{} ）的表达式，判断括号表达式是否有效，即左右括号是否匹配。
 *
 * 示例1
 * 输入：s="[({})()][]"
 * 输出：true
 *
 * 示例2
 * 输入：s="[{(]})"
 * 输出：false
 *
 * 提示
 * 0 < 表达式长度 n <= 100
 */
public class L04_Stack03 {
    public boolean isValid(String s) {
        // 正常流程：
        // 1.char 数组，遇到括号右边就入栈，阔号左边就出栈
        // 2.遍历结束，栈为空就对了;
        // 边界case：
        // 1.出栈多了，超范围;
        // 2.出栈的元素和当前字符不匹配！if 判断两个变量，用if来维护映射关系

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char p = stack.pop();
                if ((p == '(' && c == ')') || (p == '[' && c == ']') || (p == '{' && c == '}')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
