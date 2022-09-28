package pro.sky.skyprocoursework2.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprocoursework2.exception.QuestionAlreadyExistException;
import pro.sky.skyprocoursework2.exception.QuestionNotFoundException;
import pro.sky.skyprocoursework2.model.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private Set<Question> questions = new HashSet<>();
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();

    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyExistException();
        }
        return question;
    }


    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>();
    }

    public Question getRandomQuestion() {
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }
}


