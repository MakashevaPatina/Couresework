package Javacore.Coursework.Service.Interface;

import Javacore.Coursework.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
