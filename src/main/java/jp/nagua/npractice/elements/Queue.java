package jp.nagua.npractice.elements;

import jp.nagua.npractice.types.QueueType;

public class Queue {

    private PracticeKit kit;
    private QueueType type;

    public Queue(PracticeKit kit, QueueType type) {
        this.kit = kit;
        this.type = type;
    }

    public PracticeKit getKit() {
        return kit;
    }

    public void setKit(PracticeKit kit) {
        this.kit = kit;
    }

    public QueueType getQueueType() {
        return type;
    }

    public void setType(QueueType type) {
        this.type = type;
    }
}
