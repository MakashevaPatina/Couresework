package Javacore.Coursework.Service;

import Javacore.Coursework.Exception.InvalidQuestionAmountException;
import Javacore.Coursework.Service.Interface.ExaminerService;
import Javacore.Coursework.Service.Interface.QuestionService;
import Javacore.Coursework.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService, @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        if (amount <= 0) {
            throw new InvalidQuestionAmountException("Сумма должна быть больше 0");
        }

        List<Question> questions = new ArrayList<>();
        int totalJavaQuestions = javaQuestionService.getAll().size();
        int totalMathQuestions = mathQuestionService.getAll().size();

        if (totalJavaQuestions + totalMathQuestions < amount) {
            throw new InvalidQuestionAmountException("Недостаточно вопросов");
        }

        int javaQuestionsCount = random.nextInt(amount + 1);
        int mathQuestionsCount = amount - javaQuestionsCount;

        if (javaQuestionsCount > totalJavaQuestions) {
            javaQuestionsCount = totalJavaQuestions;
        }
        if (mathQuestionsCount > totalMathQuestions) {
            mathQuestionsCount = totalMathQuestions;
        }

        for (int i = 0; i < javaQuestionsCount; i++) {
            questions.add(javaQuestionService.getRandomQuestion());
        }
        for (int i = 0; i < mathQuestionsCount; i++) {
            questions.add(mathQuestionService.getRandomQuestion());
        }

        Collections.shuffle(questions);
        return questions;
    }

}
