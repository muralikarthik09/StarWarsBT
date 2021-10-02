package com.example.starwarsbt;

import android.content.Context;
import android.util.Log;

import com.example.starwarsbt.model.Match;
import com.example.starwarsbt.model.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.starwarsbt.Constants.MATCHES_JSON;
import static com.example.starwarsbt.Constants.PLAYER_JSON;

public class Utils {

    private static String TAG = Utils.class.getSimpleName();

    public static List<Match> getMatchList(Context context) {
        List<Match> matches = null;
        try {
            InputStream is = context.getAssets().open(MATCHES_JSON);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String jsonString = new String(buffer, "UTF-8");
            Gson gson = new Gson();

            Type listUserType = new TypeToken<List<Match>>() {
            }.getType();
            matches = gson.fromJson(jsonString, listUserType);
            Log.d(TAG, "User List size" + matches.size());
            return matches;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public static List<Player> getPlayers(Context context) {
        List<Player> players = null;
        try {
            InputStream is = context.getAssets().open(PLAYER_JSON);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String jsonString = new String(buffer, "UTF-8");
            Gson gson = new Gson();

            Type listUserType = new TypeToken<List<Player>>() {
            }.getType();
            players = gson.fromJson(jsonString, listUserType);
            Log.d(TAG, "User List size" + players.size());
            return players;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    public static List<Match> getMatchListByID(Context context, int id) {
        List<Match> matchList = Utils.getMatchList(context);
        List<Player> playerList = Utils.getPlayers(context);
        List<Match> filterMatchList = new ArrayList<>();
        try {
            for (int i = 0; i < matchList.size(); i++) {
                Match match = matchList.get(i);
                Player player1 = match.getPlayer1();
                Player player2 = match.getPlayer2();
                if ((player1.getId() == id || player2.getId() == id)) {
                    match.getPlayer1().setName(playerList.get(player1.getId() - 1).getName());
                    match.getPlayer2().setName(playerList.get(player2.getId() - 1).getName());
                    filterMatchList.add(match);
                }
            }
        } catch (java.util.ConcurrentModificationException exception) {
            exception.printStackTrace();
        } catch (Throwable throwable) {
        }

        return filterMatchList;


    }

    public static List<Player> getPlayersScores(Context context) {
        List<Match> matchList = Utils.getMatchList(context);
        final List<Player> playerList = Utils.getPlayers(context);
        matchList.sort(new Comparator<Match>() {
            @Override
            public int compare(Match o1, Match o2) {
                if (o1.getPlayer1().getScore() > o1.getPlayer2().getScore()) {
                    Player player = o1.getPlayer1();
                    int id = player.getId() - 1;
                    int points = playerList.get(id).getPoints();
                    playerList.get(id).setPoints(points + 3);
                } else if (o1.getPlayer1().getScore() < o1.getPlayer2().getScore()) {
                    Player player = o1.getPlayer2();
                    int id = player.getId() - 1;
                    int points = playerList.get(id).getPoints();
                    playerList.get(id).setPoints(points + 3);
                } else {
                    Player player1 = o1.getPlayer1();
                    int id = player1.getId() - 1;
                    int points = playerList.get(id).getPoints();
                    playerList.get(id).setPoints(points + 1);
                    Player player2 = o1.getPlayer2();
                    int id2 = player2.getId() - 1;
                    int points2 = playerList.get(id2).getPoints();
                    playerList.get(id2).setPoints(points2 + 1);

                }
                return 1;
            }
        });
        playerList.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getPoints() == o2.getPoints()) {
                    return o1.getName().compareTo(o2.getName());
                } else {
                    return o2.getPoints() - o1.getPoints();
                }
            }
        });
        return playerList;
    }

}
