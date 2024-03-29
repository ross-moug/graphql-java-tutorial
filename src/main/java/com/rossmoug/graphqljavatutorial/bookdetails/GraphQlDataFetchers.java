package com.rossmoug.graphqljavatutorial.bookdetails;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static java.util.Arrays.*;

@Component
public class GraphQlDataFetchers {

  private static List<Map<String, String>> books = asList(
      of("id", "book-1",
          "name", "Harry Potter and the Philosopher's Stone",
          "pageCount", "223",
          "authorId", "author-1"),
      of("id", "book-2",
          "name", "Moby Dick",
          "pageCount", "635",
          "authorId", "author-2"),
      of("id", "book-3",
          "name", "Interview with the vampire",
          "pageCount", "371",
          "authorId", "author-3")
  );

  private static List<Map<String, String>> authors = asList(
      of("id", "author-1",
          "firstName", "Joanne",
          "lastName", "Rowling"),
      of("id", "author-2",
          "firstName", "Herman",
          "lastName", "Melville"),
      of("id", "author-3",
          "firstName", "Anne",
          "lastName", "Rice")
  );

  public DataFetcher getBookByIdDataFetcher() {
    return dataFetchingEnvironment -> {
      final String bookId = dataFetchingEnvironment.getArgument("id");
      return books.stream()
                  .filter(book -> book.get("id")
                                      .equals(bookId))
                  .findFirst()
                  .orElse(null);
    };
  }

  public DataFetcher getAuthorDataFetcher() {
    return dataFetchingEnvironment -> {
      final Map<String, String> book = dataFetchingEnvironment.getSource();
      final String authorId = book.get("authorId");
      return authors.stream()
                    .filter(author -> author.get("id")
                                            .equals(authorId))
                    .findFirst()
                    .orElse(null);
    };
  }
}
