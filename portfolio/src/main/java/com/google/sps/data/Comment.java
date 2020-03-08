package com.google.sps.data;

/** A commennt will have the following fields */
public final class Comment {

  private final long id;
  private final String content;
  private final long timestamp;

  public Comment(long id, String content, long timestamp) {
    this.id = id;
    this.content = content;
    this.timestamp = timestamp;
  }
}