package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileController {

	protected Path filepath;

	public FileController(String filepath) {
		this.filepath = Paths.get(filepath);
	}

	public List<String> read() {
		try (Stream<String> lines = Files.lines(filepath)) {
			List<String> strings = lines.collect(Collectors.toList());
			return strings;
		} catch (Exception ex) {
			return null;
		}
	}

	public Integer create(String string) {
		List<String> strings = read();
		Integer newId = 1;

		if (strings.size() > 0) {

			newId = strings.stream().map(x -> {
				return Integer.parseInt(x.split(",")[0]);
			}).max((x, y) -> {
				return x.compareTo(y);
			}).get() + 1;

		}
		strings.add(string);

		try {
			String newString = string.replaceFirst("0", newId + "") + "\n";
			Files.write(filepath, newString.getBytes(), StandardOpenOption.APPEND);
			return newId;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean update(Integer stringId, String string) {
		List<String> strings = read();
		if (strings.size() == 0) {
			return false;
		}

		for (String row : strings) {
			if (Integer.parseInt(row.split(",")[0]) == stringId) {
				if (strings.remove(row)) {
					break;
				} else {
					return false;
				}
			}
		}
		strings.add(string);

		try {
			Files.write(filepath, strings);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean delete(Integer stringId) {
		List<String> strings = read();
		if (strings.size() == 0) {
			return false;
		}
		for (String string : strings) {
			if (Integer.parseInt(string.split(",")[0]) == stringId) {
				if (strings.remove(string)) {
					break;
				} else {
					return false;
				}
			}
		}

		try {
			Files.write(filepath, strings);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public String getNameById(Integer id) {
		List<String> strings = read();
		if (strings.size() == 0) {
			return null;
		}

		for (String rowString : strings) {
			String[] row = rowString.split(",");
			if (Integer.parseInt(row[0]) == id) {
				return row[1];
			}
		}
		return null;
	}

	public String getIdByName(String name) {
		List<String> strings = read();
		if (strings.size() == 0) {
			return null;
		}

		for (String rowString : strings) {
			String[] row = rowString.split(",");
			if (row[1].equals(name)) {
				return row[0];
			}
		}
		return null;
	}
}
