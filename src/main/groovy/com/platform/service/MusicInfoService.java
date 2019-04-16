package com.platform.service;


import com.platform.dao.domain.MusicInfo;

import java.util.List;

public interface MusicInfoService {

    public List<MusicInfo> getMusicInfo(MusicInfo musicInfo);
}