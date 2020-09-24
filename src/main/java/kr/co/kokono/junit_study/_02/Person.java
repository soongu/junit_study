package kr.co.kokono.junit_study._02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {

    private List<Question> characteristics = new ArrayList<>();

    public void add(Question characteristic) {
        characteristics.add(characteristic);
    }

    public List<Question> getCharacteristics() {
        return characteristics;
    }

    public List<Question> withCharacteristic(String questionPattern) {
        return characteristics.stream()
                .filter(c -> c.getText().endsWith(questionPattern))
                .collect(Collectors.toList());
    }
}
/*
// your answer
// their answer
// how important is it to you

me very organized
you very organized
very important

me no
you no


irrelevant 0
little 1
10
50
mandatory 250

how much did other person satisfy?

      multiply scores
      take nth root

      .98 * .94 take sqrt (2 questions)

      (geometric mean)

*/
