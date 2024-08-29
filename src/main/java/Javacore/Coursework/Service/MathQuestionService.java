package Javacore.Coursework.Service;

import Javacore.Coursework.Repository.Interface.QuestionRepository;
import Javacore.Coursework.Service.Interface.QuestionService;
import Javacore.Coursework.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return questionRepository.add(new Question(question, answer));
    }

    @Override
    public Question remove(String question, String answer) {
        return questionRepository.remove(new Question(question, answer));
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionList = new ArrayList<>(questionRepository.getAll());
        return questionList.get(new Random().nextInt(questionList.size()));
    }


}
