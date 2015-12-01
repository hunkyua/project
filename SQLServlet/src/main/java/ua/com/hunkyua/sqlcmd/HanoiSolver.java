//package ua.com.hunkyua.sqlcmd;
//
//import java.util.Stack;
//
///**
// * Created by Hunky on 29.11.2015.
// */
//
//    public class HanoiSolver {
//        public static void exchange(
//                Stack from, Stack help, Stack to, int count) {
//            if (count > 0) {
//                exchange(from, to, help, count-1);
//                int biggest = (int) from.pop();
//                to.push(biggest);
//                exchange(help, from, to, count-1);
//            }
//        }
//    }