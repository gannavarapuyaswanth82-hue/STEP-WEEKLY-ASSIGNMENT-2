
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Question {
    String correct, answer;
    double points;

    Question(String correct, String answer, double points) {
        this.correct = correct;
        this.answer = answer;
        this.points = points;
    }

    abstract double grade();
}

class MCQ extends Question {
    MCQ(String c, String a, double p) {
        super(c, a, p);
    }

    double grade() {
        return correct.equals(answer) ? points : 0;
    }
}

class TF extends Question {
    TF(String c, String a, double p) {
        super(c, a, p);
    }

    double grade() {
        return correct.equals(answer) ? points : 0;
    }
}

class Essay extends Question {
    Essay(String c, String a, double p) {
        super(c, a, p);
    }

    double grade() {
        String[] words = correct.toLowerCase().split(",");
        String text = answer.toLowerCase();
        int count = 0;

        for (String word : words) {
            if (text.contains(word.trim())) {
                count++;
            }
        }

        if (count >= 2) return points * 0.75;
        if (count == 1) return points * 0.50;
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        double total = 0;

        Pattern pattern = Pattern.compile("\"([^\"]*)\"");

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String type = line.substring(0, line.indexOf(' '));

            Matcher matcher = pattern.matcher(line);
            String[] data = new String[3];
            int j = 0;

            while (matcher.find()) {
                data[j++] = matcher.group(1);
            }

            double points = Double.parseDouble(
                line.substring(line.lastIndexOf('"') + 1).trim()
            );

            Question q;

            if (type.equals("MCQ")) {
                q = new MCQ(data[1], data[2], points);
            } else if (type.equals("TF")) {
                q = new TF(data[1], data[2], points);
            } else {
                q = new Essay(data[1], data[2], points);
            }

            double score = q.grade();
            System.out.printf("%s: %.2f%n", type, score);
            total += score;
        }

        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}
