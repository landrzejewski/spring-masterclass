package pl.training.search;

import io.reactivex.Observable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ObservableReader {

    static Observable<String> from(InputStream inputStream) {
        return Observable.create(emitter -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String text;
                while ((text = reader.readLine()) != null) {
                    emitter.onNext(text);
                }
                emitter.onComplete();
            }
        });
    }

}
