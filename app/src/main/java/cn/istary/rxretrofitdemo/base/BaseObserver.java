package cn.istary.rxretrofitdemo.base;

import android.util.Log;

import java.net.ConnectException;

import cn.istary.rxretrofitdemo.util.StaticClass;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 * CREATED BY: Sinry
 * TIME: 2019/1/1 18:14
 * DESCRIPTION:
 */

public abstract class BaseObserver<T extends BaseResponse> implements Observer<T> {

    /**
     * 数据消费者的基类, 进行公有的操作, 如错误处理
     * 由于onNext需要由具体实现类实现, 所以必须定义为抽象类
     */

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(StaticClass.MYTAG, "onSubscribe()");
    }

    @Override
    public abstract void onNext(T t);

    @Override
    public void onError(Throwable e) {
        if(e instanceof ConnectException){
            onNetError("网络错误" + e.getMessage());
        }

        //如果非我们定义的错误类型, Log打印
        Log.d(StaticClass.MYTAG, e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.d(StaticClass.MYTAG, "onComplete()");
    }

    public abstract void onNetError(String errorMsg);
    //还可以再定义其他错误, 交给调用方实现
}
