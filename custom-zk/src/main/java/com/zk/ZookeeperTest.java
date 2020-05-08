package com.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZookeeperTest {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 连接服务端，连接地址可以写多个，比如"127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183"
        // 当客户端与服务端的连接断掉后就会重试去连其他的服务器地址
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 30* 1000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getType());
            }
        });


        // 创建一个节点，并设置内容，设置ACL(该节点的权限设置)， 节点类型（7种：持久节点、临时节点、持久顺序节点、临时顺序节点、容器节点、TTL节点、TTL顺序节点）
        // 容器节点
        // 创建成功则返回该节点的路径，注意顺序节点
        String a = zooKeeper.create("/luban123", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //create success:/luban123
        System.out.println("create success:"+a);


        // 获取某个节点的内容，并设置一个监听器
        // stat用来承载节点的其他信息
        Stat stat = new Stat();

        byte[] result = zooKeeper.getData("/luban123", new Watcher() {
            @Override
            public void process(WatchedEvent event) {

                System.out.println(event.getType());
            }
        }, stat);

        System.in.read();


        // 修改节点的内容，这里有乐观锁,version表示本次修改, -1表示不检查版本强制更新
        // stat表示修改数据成功之后节点的状态
//        Stat stat = zooKeeper.setData("/zk", "xxx".getBytes(), -1);

        // 删除
//        zooKeeper.delete("/zk", -1);


        // 判断某节点是否存在，如果存在则返回该节点的状态（并没有节点的内容）
        // 同时设置一个监听器
//        zooKeeper.exists("/zk", new Watcher() {
//            @Override
//            public void process(WatchedEvent event) {
//                System.out.println(event);
//            }
//        });
//        System.out.println(stat);


        // 获取孩子节点
//        List<String> children = zooKeeper.getChildren("/zk", new Watcher() {
//            @Override
//            public void process(WatchedEvent event) {
//
//            }
//        });


        // 上面的都是顺带对某个节点添加一个监听器
        // addWatch是直接对某个添加监听器，并且添加的是持久化的监听器
        // 监听器有两种，比较特殊的是递归-PERSISTENT_RECURSIVE， 表示：子节点的数据变化也会触发Watcher，而且子节点的子节点数据发生变化也会触发监听器
//        zooKeeper.addWatch("/luban123", new Watcher() {
//            @Override
//            public void process(WatchedEvent event) {
//                System.out.println("PERSISTENT_RECURSIVE"+event);
//            }
//        }, AddWatchMode.PERSISTENT_RECURSIVE);


//
//        zooKeeper.addWatch("/luban123", new Watcher() {
//            @Override
//            public void process(WatchedEvent event) {
//                System.out.println("PERSISTENT"+event);
//            }
//        }, AddWatchMode.PERSISTENT);
//
//        System.in.read();

        // 异步调用
//        String ctx = "test";
//        zooKeeper.create("/xxx1/", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL, new AsyncCallback.Create2Callback() {
//
//            @Override
//            public void processResult(int rc, String path, Object ctx, String name, Stat stat) {
//                System.out.println("rc="+ rc);
//                System.out.println("path="+ path);
//                System.out.println("ctx="+ ctx.toString());
//                System.out.println("name="+ name);
//                System.out.println("stat="+ stat);
//            }
//        }, ctx);


//        zooKeeper.addWatch("/zk", new Watcher() {
//            @Override
//            public void process(WatchedEvent event){
//                System.out.println(event);
//            }
//        }, AddWatchMode.PERSISTENT_RECURSIVE);




    }
}
