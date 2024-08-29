package Javacore.Coursework.Service.Interface;

import Javacore.Coursework.model.Question;

import java.util.Collection;
import java.util.List;


public interface QuestionService {
    Question add(String question, String answer);

    Question remove(String question, String answer);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
