package com.example.retrofitdemo;

import com.example.retrofitdemo.model.BoDe;
import com.example.retrofitdemo.model.FavoriteQuestion;
import com.example.retrofitdemo.model.HistoryDetail;
import com.example.retrofitdemo.model.HistoryTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class KetQua {
    public static String chuoi="";
    public static int idCauHoi;
    public static int lastPos=0;
    public static int curPos=0;
    public static List<Integer> idList =new ArrayList<>();
    public static TreeSet<String> selectedIn1Page=new TreeSet<>();
    public static HashMap<Integer,TreeSet<String>> dynamicSelected=new HashMap<>();
    public static List<String> listSelected=new ArrayList<String>();
    public static List<TreeSet<String>> testList=new ArrayList<>();
    public static String rdSelected="";
    //public static List<String> dapan=new ArrayList<>();
    public static HashMap<Integer,String> map=new HashMap<Integer, String>();
    public static Boolean check=false;
    public static List<BoDe> dsbode = new ArrayList<>();
    public static String token="";
    public static List<FavoriteQuestion> dsyeuthich= new ArrayList<>();
    public static FavoriteQuestion favoriteQuestion=new FavoriteQuestion();
    public static Boolean isStudy=true;
    public static ArrayList<HistoryTest> dslichsu=new ArrayList<>();
    public static String txt_ten="";
    public static HistoryDetail historyDetail;
}
