package com.pp.server.util;

import java.lang.invoke.VolatileCallSite;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.util.CollectionUtils;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTest {
	
	AtomicInteger integer = new AtomicInteger(0);
	public void testComplete()
	{
		Completable.create(new CompletableOnSubscribe() {
			
			@Override
			public void subscribe(CompletableEmitter emitter) throws Exception {
				// TODO Auto-generated method stub
				emitter.onComplete();
			}
		}).subscribe(new CompletableObserver() {
			
			@Override
			public void onSubscribe(Disposable d) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				System.out.println("执行完成");
			}
		});
	}
	
	public void testObservable()
	{
		
		 AtomicBoolean onComplete = new AtomicBoolean(false);
		
		 Observable.create(new ObservableOnSubscribe<Map<String, Object>>() {

			@Override
			public void subscribe(ObservableEmitter<Map<String, Object>> emitter) throws Exception {
				// TODO Auto-generated method stub
				Map<String, Object> paramMap = new HashMap<>();
				while(integer.get()< 100) {
					paramMap.put("userAccount", integer.incrementAndGet());
					emitter.onNext(paramMap);
					paramMap.clear();
					System.out.println(Thread.currentThread().getName()+" "+System.currentTimeMillis()+"============="+integer.get());
					paramMap.put("userAccount", integer.incrementAndGet());
					emitter.onNext(paramMap);
					System.out.println(Thread.currentThread().getName()+" "+System.currentTimeMillis()+"============="+integer.get());
				}
				emitter.onComplete();
			}
		}).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.trampoline()).
		 subscribe(new Observer<Map<String, Object>>() {

			@Override
			public void onSubscribe(Disposable d) {
				// TODO Auto-generated method stub
				System.out.println("OnSubscribe");
			}

			@Override
			public void onNext(Map<String, Object> t) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				if (CollectionUtils.isEmpty(t)) {
					return;
				}
				Iterator<Entry<String, Object>> i = t.entrySet().iterator();
				Entry<String, Object> entry = i.next();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				System.out.println("===============ONNEXT==============="+Thread.currentThread().getName()+" "+System.currentTimeMillis()+" "+entry.getKey() +" "+ entry.getValue());
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				System.out.println("OnComplete");
				onComplete.set(true);
			}
		});
		 
		while (!onComplete.get()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 }
		 
		 //.subscribeOn(Schedulers.newThread())
//		 .observeOn(Schedulers.trampoline())
		
//		Observer<Map<String, Object>> observer = new Observer<Map<String,Object>>() {
//
//			@Override
//			public void onSubscribe(Disposable d) {
//				// TODO Auto-generated method stub
//				System.out.println("-----");
//			}
//
//			@Override
//			public void onNext(Map<String, Object> t) {
//				// TODO Auto-generated method stub
//				if (CollectionUtils.isEmpty(t)) {
//					return;
//				}
//				Iterator<Entry<String, Object>> i = t.entrySet().iterator();
//				Entry<String, Object> entry = i.next();
////				try {
////					Thread.sleep(10000);
////				} catch (InterruptedException e) {
////					e.printStackTrace();
////				}
//				System.out.println("===============ONNEXT==============="+Thread.currentThread().getName()+" "+System.currentTimeMillis()+" "+entry.getKey() +" "+ entry.getValue());
//				
//			}
//
//			@Override
//			public void onError(Throwable e) {
//				// TODO Auto-generated method stub
//			}
//
//			@Override
//			public void onComplete() {
//				// TODO Auto-generated method stub
//			}
//		};
////		observable.subscribe(observer);
//		observable.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.trampoline()).subscribe(observer);
		
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
				//e.printStackTrace();
				//System.out.println("send exception");
			}
		});
	}
	
	public static void main(String[] args) {
		RxJavaTest rxJavaTest = new RxJavaTest();
		rxJavaTest.testObservable();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
