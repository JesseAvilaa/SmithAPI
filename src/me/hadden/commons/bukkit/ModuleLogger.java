package me.hadden.commons.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class ModuleLogger {

  private String prefix;
  private CommandSender sender;

  public ModuleLogger() {
    this.prefix = "[Smith:API] ";
    this.sender = Bukkit.getConsoleSender();
  }

  public ModuleLogger(String prefix) {
    this.prefix = prefix;
    this.sender = Bukkit.getConsoleSender();
  }

  public void info(String message) {
    this.log(InfoLevel.INFO, message);
  }

  public void warning(String message) {
    this.log(InfoLevel.WARNING, message);
  }

  public void severe(String message) {
    this.log(InfoLevel.SEVERE, message);
  }

  public void log(InfoLevel level, String message) {
    this.log(level, message, null);
  }

  public void log(InfoLevel level, String message, Throwable throwable) {
    StringBuilder result = new StringBuilder(this.prefix + message);
    if (throwable != null) {
      result.append("\n" + throwable.getMessage());
      for (StackTraceElement ste : throwable.getStackTrace()) {
        if (ste.toString().contains("io.github.losteddev.skywars")) {
          result.append("\n" + ste.toString());
        }
      }
    }

    this.sender.sendMessage(level.format(result.toString()));
  }

  public ModuleLogger getModule(String module) {
    return new ModuleLogger(this.prefix + "[" + module + "] ");
  }

  public static enum InfoLevel {
    INFO("§a"),
    WARNING("§c"),
    SEVERE("§4");

    private String color;

    InfoLevel(String color) {
      this.color = color;
    }

    public String format(String message) {
      return this.color + message;
    }
  }
}
