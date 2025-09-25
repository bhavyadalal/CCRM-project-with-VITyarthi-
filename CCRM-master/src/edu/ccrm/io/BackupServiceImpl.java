package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BackupServiceImpl implements BackupService {

    @Override
    public void createBackup(String sourceDirectory, String backupBaseDirectory) throws IOException {
        // Create a timestamped folder for the backup
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupDirectory = Paths.get(backupBaseDirectory, "backup_" + timestamp);
        Files.createDirectories(backupDirectory);

        // Copy files from sourceDirectory to the backupDirectory
        try (Stream<Path> paths = Files.walk(Paths.get(sourceDirectory))) {
            paths.filter(Files::isRegularFile)
                 .forEach(sourcePath -> {
                     try {
                         Path destinationPath = backupDirectory.resolve(Paths.get(sourceDirectory).relativize(sourcePath));
                         Files.copy(sourcePath, destinationPath);
                     } catch (IOException e) {
                         System.err.println("Error copying file " + sourcePath + ": " + e.getMessage());
                     }
                 });
        }
        System.out.println("Backup created at: " + backupDirectory.toAbsolutePath());
    }
}
