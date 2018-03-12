package network.utils;

import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//todo 线程池优化
public class ThreadPoolUtils {

	private static final String ERROR_THREAD_KEY_NULL = "error_thread_key_null";

	private ThreadPoolUtils() {

	}

    //线程池核心线程数
	private static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

	//线程池最大线程数
    private static int MAX_POOL_SIZE = 100;

    //额外线程空状态生存时间
    private static int KEEP_ALIVE_TIME = 10000;

    //阻塞队列，当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程
    private static BlockingQueue<Runnable> workQueue=
            new ArrayBlockingQueue<>(10);

    //线程工厂
    private static ThreadFactory threadFactory =
    		new ThreadFactory(){

    	private final AtomicInteger integer =new AtomicInteger();

				@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "myThreadPool thread:"+integer.getAndIncrement());
		}

			};

	private static ThreadPoolExecutor threadPool;

	/*
     * 线程关键词管理
     */
    private static Vector<String> v_RunnableManagers = new Vector<>();

	static{
    	threadPool = new ThreadPoolExecutor(
				CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
				TimeUnit.SECONDS, workQueue,threadFactory);
    	v_RunnableManagers.clear();
    }

	/*
     * 从线程池中抽取线程。执行指定的Runnable对象
     */
    public static boolean execute(String key, Runnable runnable){

		if(canAdd(key)){
    		threadPool.execute(runnable);
//    		threadPool.
//    		runnable.
//    		threadPool.remove(workQueue.)
    		return true;
    	}
    	else{
    		return false;
    	}
    }


	//---------线程关键词管理
    /*
     * 是否能加线程进线程次，关键词重复判断
     */
	public static synchronized boolean canAdd(String key) {
		if (key == null) {
			throw new RuntimeException(ERROR_THREAD_KEY_NULL);
		}
		if(key.length()==0)return true;

		for(int i=0;i<v_RunnableManagers.size();i++){
    		if(v_RunnableManagers.get(i).equals(key)){
    			return false;
    		}
    	}
    	v_RunnableManagers.add(key);
    	return true;
    }
    
    /*
	 * 线程运行完毕，关键词删除
     * 添加同步关键字 防止多线程访问导致的指针越界异常
     */
	public static synchronized void isfinish(String key) {
		for(int i=0;i<v_RunnableManagers.size();i++){
     		if(v_RunnableManagers.get(i).equals(key)){
     			v_RunnableManagers.remove(i);
     		}
     	}
    }
}
