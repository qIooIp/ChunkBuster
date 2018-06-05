package me.qiooip.buster.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BusterManager {

    private Map<UUID, BusterProfile> profiles;

    public BusterManager() {
        this.profiles = new HashMap<>();
    }
}
