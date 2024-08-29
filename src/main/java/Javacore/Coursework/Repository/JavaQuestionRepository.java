package Javacore.Coursework.Repository;

import Javacore.Coursework.Exception.QuestionNotFoundException;
import Javacore.Coursework.Repository.Interface.QuestionRepository;
import Javacore.Coursework.model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
@Primary
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @PostConstruct
    public void init() {
        add(new Question("Что такое JDK?", "JDK — это набор инструментов для разработки на Java."));
        add(new Question("Что такое JVM?", "JVM — это виртуальная машина для выполнения байт-кода Java."));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        } else {
            throw new QuestionNotFoundException("Вопрос не найден.");
        }
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }
}
