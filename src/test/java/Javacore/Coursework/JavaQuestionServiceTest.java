package Javacore.Coursework;

import Javacore.Coursework.Repository.Interface.QuestionRepository;
import Javacore.Coursework.Service.JavaQuestionService;
import Javacore.Coursework.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JavaQuestionServiceTest {

    private QuestionRepository javaQuestionRepository;
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionRepository = mock(QuestionRepository.class);
        javaQuestionService = new JavaQuestionService(javaQuestionRepository);
    }

    @Test
    void addQuestion_ShouldAddQuestion() {
        Question question = new Question("Вопрос1", "Ответ1");
        when(javaQuestionRepository.add(question)).thenReturn(question);

        Question addedQuestion = javaQuestionService.add("Вопрос1", "Ответ1");

        assertEquals(question, addedQuestion);
    }

    @Test
    void removeQuestion_ShouldRemoveQuestion() {
        Question question = new Question("Вопрос2", "Ответ2");
        when(javaQuestionRepository.remove(question)).thenReturn(question);

        Question removedQuestion = javaQuestionService.remove("Вопрос2", "Ответ2");

        assertEquals(question, removedQuestion);
    }

    @Test
    void getAll_ShouldReturnAllQuestions() {
        List<Question> expectedQuestions = List.of(
                new Question("Вопрос1", "Ответ1"),
                new Question("Вопрос2", "Ответ2")
        );
        when(javaQuestionRepository.getAll()).thenReturn(expectedQuestions);

        List<Question> actualQuestions = (List<Question>) javaQuestionService.getAll();

        assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    void getRandomQuestion_ShouldReturnRandomQuestion() {
        List<Question> questions = List.of(
                new Question("Вопрос1", "Ответ1"),
                new Question("Вопрос2", "Ответ2")
        );
        when(javaQuestionRepository.getAll()).thenReturn(questions);

        Question randomQuestion = javaQuestionService.getRandomQuestion();

        assertTrue(questions.contains(randomQuestion));
    }
}
