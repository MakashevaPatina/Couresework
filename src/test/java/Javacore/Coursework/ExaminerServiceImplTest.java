package Javacore.Coursework;

import Javacore.Coursework.Exception.InvalidQuestionAmountException;
import Javacore.Coursework.Service.ExaminerServiceImpl;
import Javacore.Coursework.Service.Interface.QuestionService;
import Javacore.Coursework.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExaminerServiceImplTest {
    private QuestionService javaQuestionService;
    private QuestionService mathQuestionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        javaQuestionService = mock(QuestionService.class);
        mathQuestionService = mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

    @Test
    void getQuestions_ShouldReturnCorrectNumberOfQuestions() {
        List<Question> javaQuestions = List.of(
                new Question("вопрос", "ответ")
        );
        List<Question> mathQuestions = List.of(
                new Question("=2 + 5?", "7")
        );

        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);
        when(javaQuestionService.getRandomQuestion()).thenReturn(javaQuestions.get(0));
        when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestions.get(0));

        List<Question> questions = examinerService.getQuestions(2);

        assertEquals(2, questions.size());
        assertTrue(questions.containsAll(javaQuestions));
        assertTrue(questions.containsAll(mathQuestions));
    }

    @Test
    void getQuestions_ShouldThrowExceptionWhenNotEnoughQuestions() {
        when(javaQuestionService.getAll()).thenReturn(new ArrayList<>());
        when(mathQuestionService.getAll()).thenReturn(new ArrayList<>());

        assertThrows(InvalidQuestionAmountException.class, () -> examinerService.getQuestions(1));
    }
    @Test
    void getQuestions_ShouldThrowException_WhenAmountIsZeroOrNegative() {
        assertThrows(InvalidQuestionAmountException.class, () -> examinerService.getQuestions(0));
        assertThrows(InvalidQuestionAmountException.class, () -> examinerService.getQuestions(-1));
    }
    @Test
    void getQuestions_ShouldThrowException_WhenNotEnoughQuestions() {
        when(javaQuestionService.getAll()).thenReturn(Collections.emptySet());
        when(mathQuestionService.getAll()).thenReturn(Collections.emptySet());

        assertThrows(InvalidQuestionAmountException.class, () -> examinerService.getQuestions(1));
    }

}