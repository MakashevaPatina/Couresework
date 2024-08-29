package Javacore.Coursework;

import Javacore.Coursework.Repository.Interface.QuestionRepository;
import Javacore.Coursework.Service.MathQuestionService;
import Javacore.Coursework.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MathQuestionServiceTest {
    private QuestionRepository mathQuestionRepository;
    private MathQuestionService mathQuestionService;

    @BeforeEach
    void setUp() {
        mathQuestionRepository = mock(QuestionRepository.class);
        mathQuestionService = new MathQuestionService(mathQuestionRepository);
    }

    @Test
    void addQuestion_ShouldAddQuestion() {
        Question question = new Question("2 + 2?", "4");
        when(mathQuestionRepository.add(question)).thenReturn(question);

        Question addedQuestion = mathQuestionService.add("2 + 2?", "4");

        assertEquals(question, addedQuestion);
    }
    @Test
    void removeQuestion_ShouldRemoveQuestion() {
        Question question = new Question("2 + 2?", "4");
        when(mathQuestionRepository.remove(question)).thenReturn(question);

        Question removedQuestion = mathQuestionService.remove("2 + 2?", "4");

        assertEquals(question, removedQuestion);
    }

    @Test
    void getAll_ShouldReturnAllQuestions() {
        List<Question> expectedQuestions = List.of(
                new Question("2 + 2?", "4"),
                new Question("3 * 3?", "9")
        );
        when(mathQuestionRepository.getAll()).thenReturn(expectedQuestions);

        List<Question> actualQuestions = (List<Question>) mathQuestionService.getAll();

        assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    void getRandomQuestion_ShouldReturnRandomQuestion() {
        List<Question> questions = List.of(
                new Question("2 + 2?", "4"),
                new Question("3 * 3?", "9")
        );
        when(mathQuestionRepository.getAll()).thenReturn(questions);

        Question randomQuestion = mathQuestionService.getRandomQuestion();

        assertTrue(questions.contains(randomQuestion));
    }
}
