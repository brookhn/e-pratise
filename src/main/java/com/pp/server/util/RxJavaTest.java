package com.pp.server.util;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;

public class RxJavaTest {
	
	public void testComplete()
	{
		
	}
	
	public void testSingle() {
		Single.create(new SingleOnSubscribe<String>() {

			@Override
			public void subscribe(SingleEmitter<String> emitter) throws Exception {
				// TODO Auto-generated method stub
				//emitter.onSuccess("我要走了，不要联系我");
				emitter.onError(new Exception("发生异常了"));
			}
		}).subscribe(new SingleObserver() {

			@Override
			public void onSubscribe(Disposable d) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(Object t) {
				// TODO Auto-generated method stub
				System.out.println("on Success "+ t.toString());
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				e.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
		RxJavaTest rxJavaTest = new RxJavaTest();
		rxJavaTest.testSingle();
//		Observable<String> observer = Observable.create(new ObservableOnSubscribe<String>() {
//
//			@Override
//			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//				emitter.onNext("播放第一集");
//				emitter.onNext("播放第二集");
//				emitter.onNext("播放第三集");
//				emitter.onNext("播放第四集");
//			}
//		});
//		
//		Observer reader = new Observer<String>() {
//
//			@Override
//			public void onSubscribe(Disposable d) {
//				System.out.println("onSubscribe");
//			}
//
//			@Override
//			public void onNext(String t) {
//				System.out.println("onNext "+t);
//			}
//
//			@Override
//			public void onError(Throwable e) {
//				System.out.println("onError");
//			}
//
//			@Override
//			public void onComplete() {
//				// TODO Auto-generated method stub
//				System.out.println("onComplete");
//			}
//		};
//		observer.subscribe(reader);
		
	}
}
