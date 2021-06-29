/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.exam0604;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * 音乐播放
 */
public class MusicPlayer {

    private final LinkedList<Music> musicList;

    private final int capacity;

    /**
     * 播放器初始化容量并记录
     *
     * @param capacity 播放器容量
     */
    public MusicPlayer(int capacity) {
        this.musicList = new LinkedList<>();
        this.capacity = capacity;
    }

    /**
     * 添加音乐
     *
     * @param musicId 音乐id
     * @return 添加成功返回0 已存在返回-1 容量不足则返回删除的音乐id
     */
    public int addMusic(int musicId) {
        Optional<Music> opt = musicList.stream().filter(music -> music.getMusicId() == musicId).findAny();
        if (opt.isPresent()) {
            return -1;
        }
        if (musicList.size() < capacity) {
            // enough space
            musicList.add(new Music(musicId));
            return 0;
        }
        // not more empty, delete music by rule
        musicList.sort((e1, e2) -> {
            if (e1.getPlayCount() != 0 && e2.getPlayCount() != 0) {
                if (e1.getPlayCount() != e2.getPlayCount()) {
                    return -Integer.compare(e1.getPlayCount(), e2.getPlayCount());
                } else {
                    return -Long.compare(e1.getPlayAt().get(0), e2.getPlayAt().get(0));
                }
            }
            if (e1.getPlayCount() != 0 && e2.getPlayCount() == 0) {
                return -1;
            } else if (e1.getPlayCount() == 0 && e2.getPlayCount() != 0) {
                return 1;
            } else {
                return -Long.compare(e1.getCreateAt(), e2.getCreateAt());
            }
        });
        Music removeMusic = musicList.removeLast();
        musicList.add(new Music(musicId));
        return removeMusic.getMusicId();
    }

    /**
     * 播放音乐
     *
     * @param musicId 音乐id
     * @return 是否播放成功
     */
    public boolean playMusic(int musicId) {
        Optional<Music> opt = musicList.stream().filter(music -> music.getMusicId() == musicId).findAny();
        if (opt.isEmpty()) {
            return false;
        }
        opt.get().setPlayCount(opt.get().getPlayCount() + 1);
        opt.get().getPlayAt().add(System.nanoTime());
        return true;
    }

    /**
     * 删除音乐
     *
     * @param musicId 待删除音乐id
     * @return 是否删除成功
     */
    public boolean deleteMusic(int musicId) {
        Optional<Music> opt = musicList.stream().filter(music -> music.getMusicId() == musicId).findAny();
        if (opt.isEmpty()) {
            return false;
        }
        musicList.remove(opt.get());
        return true;
    }

}

/**
 * 音乐
 */
class Music {

    private int musicId;

    private long createAt;

    private int playCount;

    private final List<Long> playAt;

    Music(int musicId) {
        this.musicId = musicId;
        this.createAt = System.nanoTime();
        this.playCount = 0;
        playAt = new ArrayList<>(0);
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public List<Long> getPlayAt() {
        return playAt;
    }
}
