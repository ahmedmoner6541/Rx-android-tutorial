package com.ahmedmoner.rxandroid_1;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cold_Observable();
        hotObservable_ConnectableObservableConnectableObservable();

        hotObservable();


    }

    private void hotObservable() {
        // TODO: 9/15/2021  في الطريقه دي هيتتم انشائه اصلا hot من البدايه واسمها suject

        buplish_Supject_Implementation();
        behavior_Supject_Implementation();
        ReplaySubject_Supject_Implementation();
        async_Supject_Implementation();
    }

    private void cold_Observable() {
        // TODO: 9/15/2021   ده اي حد جديد بيخش عليه بيبدا معاه من جديد
        Observable<Long> old = Observable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS);

        old.subscribe(i -> Log.d(TAG, "onCreate: student 1 " + i));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        old.subscribe(i -> Log.d(TAG, "onCreate: student 2 " + i));
    }

    private void hotObservable_ConnectableObservableConnectableObservable() {
        // الطريقه الاولي لانشاء ال hot  هو ي تحويل من cold to hot
        //اسمها connectable observable

        ConnectableObservable<Long> old = ConnectableObservable///////////////////////// ******
                .intervalRange(0, 5, 0, 1, TimeUnit.SECONDS).publish();

        old.connect();

        old.subscribe(i -> Log.d(TAG, "onCreate: student 1 " + i));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        old.subscribe(i -> Log.d(TAG, "onCreate: student 2 " + i));
    }

    // 1
    private void buplish_Supject_Implementation() {
        PublishSubject<String> subject = PublishSubject.create();

        subject.subscribe(i -> Log.d(TAG, "student First: " + i));

        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");
        subject.subscribe(i -> Log.d(TAG, "student Secound: " + i));
        sleep(1000);
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("J");
        sleep(1000);


    }

    //  2
    private void behavior_Supject_Implementation() {
//todo بيسمع قبل اللمعلوماتت اللي دخل عليها بمعلومه واحده
        BehaviorSubject<String> subject = BehaviorSubject.create();

        subject.subscribe(i -> Log.d(TAG, "student First: " + i));

        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");
        subject.subscribe(i -> Log.d(TAG, "student Secound: " + i));
        sleep(1000);
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("J");
        sleep(1000);

    }

    //  3
    private void ReplaySubject_Supject_Implementation() {
        // TODO: 9/15/2021  دا الطالب لاسئيل اللي عاوزالشرح من الاول
        ReplaySubject<String> subject = ReplaySubject.create();

        subject.subscribe(i -> Log.d(TAG, "student First: " + i));

        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");
        subject.subscribe(i -> Log.d(TAG, "student Secound: " + i));
        sleep(1000);
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("J");
        sleep(1000);
    }

    //  4
    private void async_Supject_Implementation() {
        // الطالب الروش دا بيهتم باخر معلومه فقط
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.subscribe(i -> Log.d(TAG, "student First: " + i));

        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");
        subject.subscribe(i -> Log.d(TAG, "student Secound: " + i));
        sleep(1000);
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("J");
        sleep(1000);

        subject.onComplete(); ////////////// ************** //////////////
    }


    public void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}