package Javacore.Coursework.Service;

import Javacore.Coursework.Repository.Interface.QuestionRepository;
import Javacore.Coursework.Service.Interface.QuestionService;
import Javacore.Coursework.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository questionRepository;

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository questionRepository) {
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

