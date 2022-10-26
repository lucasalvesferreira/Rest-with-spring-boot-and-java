//package com.api.Restwithspringbootandjava.contrllers;
//
//import com.api.Restwithspringbootandjava.exceptions.UnsopportedMathOperationException;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.concurrent.atomic.AtomicLong;
//
//public class MathController {
//
//
//    private final AtomicLong counter = new AtomicLong();
//
//        private SimpleMath math = new SimpleMath();
//
//        @RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
//                method= RequestMethod.GET)
//        public Double sum(
//                @PathVariable(value = "numberOne") String numberOne,
//                @PathVariable(value = "numberTwo") String numberTwo
//        ) throws Exception{
//
//            if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
//                throw new UnsopportedMathOperationException("Please set a numeric value!");
//            }
//            return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
//        }
//
//        @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}",
//                method=RequestMethod.GET)
//        public Double subtraction(
//                @PathVariable(value = "numberOne") String numberOne,
//                @PathVariable(value = "numberTwo") String numberTwo
//        ) throws Exception{
//
//            if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
//                throw new UnsopportedMathOperationException("Please set a numeric value!");
//            }
//            return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
//        }
//
//        @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}",
//                method=RequestMethod.GET)
//        public Double multiplication(
//                @PathVariable(value = "numberOne") String numberOne,
//                @PathVariable(value = "numberTwo") String numberTwo
//        ) throws Exception{
//
//            if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
//                throw new UnsopportedMathOperationException("Please set a numeric value!");
//            }
//            return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
//        }
//
//        @RequestMapping(value = "/division/{numberOne}/{numberTwo}",
//                method=RequestMethod.GET)
//        public Double division(
//                @PathVariable(value = "numberOne") String numberOne,
//                @PathVariable(value = "numberTwo") String numberTwo
//        ) throws Exception{
//
//            if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
//                throw new UnsopportedMathOperationException("Please set a numeric value!");
//            }
//            return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
//        }
//
//        @RequestMapping(value = "/mean/{numberOne}/{numberTwo}",
//                method=RequestMethod.GET)
//        public Double mean(
//                @PathVariable(value = "numberOne") String numberOne,
//                @PathVariable(value = "numberTwo") String numberTwo
//        ) throws Exception{
//
//            if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
//                throw new UnsopportedMathOperationException("Please set a numeric value!");
//            }
//            return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
//        }
//
//        @RequestMapping(value = "/squareRoot/{number}",
//                method=RequestMethod.GET)
//        public Double squareRoot(
//                @PathVariable(value = "number") String number
//        ) throws Exception{
//
//            if(!NumberConverter.isNumeric(number)) {
//                throw new UnsopportedMathOperationException("Please set a numeric value!");
//            }
//            return math.squareRoot(NumberConverter.convertToDouble(number));
//        }
//    }
//
//
