package io.github.Skepter.AllAssets.Utils;

import io.github.Skepter.AllAssets.AllAssets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IDReader {

	public static String readID(String str) {

		File csvFile = new File(AllAssets.instance().getDataFolder(), "ItemData.csv");
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\t";

		try {
			br = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] data = line.split(cvsSplitBy);
				if (data[0].equalsIgnoreCase(str))
					return data[1] + ":" + data[2];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}