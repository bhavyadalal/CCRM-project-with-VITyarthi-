package edu.ccrm.io;

import java.io.IOException;

public interface BackupService {
    void createBackup(String sourceDirectory, String backupBaseDirectory) throws IOException;
}
