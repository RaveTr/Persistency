package com.mememan.persistency.util;

import com.mememan.persistency.PersistencyConstants;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public final class FileUtil {

    private FileUtil() {
        throw new IllegalAccessError("Attempted to construct a Utility Class!");
    }

    /**
     * Copied/ported version of the old Forge {@code FileUtils#getOrCreateDirectory(Path, String)}, with a few slight adjustments.
     *
     * @param dirPath The target directory in which the new directory should be created.
     * @param dirLabel The folder name of the new directory.
     *
     * @return The newly created directory, as a {@link Path} object.
     */
    public static Path getOrCreateDirectory(Path dirPath, String dirLabel) {
        if (!Files.isDirectory(dirPath.getParent(), new LinkOption[0])) getOrCreateDirectory(dirPath.getParent(), "parent of " + dirLabel);

        if (!Files.isDirectory(dirPath, new LinkOption[0])) {
            PersistencyConstants.LOGGER.debug("Creating '{}' directory in: {}", dirLabel, dirPath);

            try {
                Files.createDirectory(dirPath);
            } catch (IOException exception) {
                if (exception instanceof FileAlreadyExistsException) PersistencyConstants.LOGGER.error("Failed to create '{}' directory - A file with the same name exists in the target directory", dirLabel);
                else PersistencyConstants.LOGGER.error("Problem with creating {} directory (Permissions?)", dirLabel, exception);

                throw new RuntimeException("Problem creating directory", exception);
            }

            PersistencyConstants.LOGGER.debug("Created '{}' directory in: {}", dirLabel, dirPath);
        } else PersistencyConstants.LOGGER.warn("Found existing {} directory : {}, nothing new will happen", dirLabel, dirPath);

        return dirPath;
    }
}
