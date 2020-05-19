package DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.*;

public class DbCon {


    private static String user = "p201606023";
    private static String password = "pp201606023";
    private static String host = "l.bsks.ac.kr";
    private static int port = 22;

    private static String r_host = "localhost";
    private static int l_port = 4321;
    private static int r_port = 3306;

    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    private static String url = "jdbc:mysql://" + r_host +":" + l_port + "/";
    private static String db = "p201606023";
    private static String dbUser = "p201606023";
    private static String dbPasswd = "pp201606023";

    private static DbCon instance;

    private DbCon() {}

    public static DbCon getInstance() throws Exception {

        if(instance==null)
        {
            instance = new DbCon();
            JSch jsch = new JSch();
            Session j_session = jsch.getSession(user, host, port);

            j_session.setPassword(password);
            j_session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            j_session.connect();
            int assinged_port = j_session.setPortForwardingL(l_port, r_host, r_port);
            System.out.println("localhost:"+assinged_port+" -> "+ r_host +":"+ r_port);

            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url+db, dbUser, dbPasswd);
            System.out.println("connection");
        }
        return instance;
    }

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(url+db, dbUser, dbPasswd);
    }

    //obj_list
    public List<Obj_listDTO> getObjlist(int topidx) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Obj_listDTO> objList = null;

        String query = "SELECT * FROM obj_list WHERE top_idx=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(topidx));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                objList = new ArrayList<Obj_listDTO>();

                do {
                    Obj_listDTO obj = new Obj_listDTO();

                    obj.setNumber(rs.getInt("number"));
                    obj.setIdx(rs.getInt("idx"));
                    obj.setSubject(rs.getString("subject"));
                    obj.setKind(rs.getString("kind"));
                    obj.setNum(rs.getString("num"));
                    obj.setView(rs.getString("view"));
                    obj.setCh1(rs.getString("ch1"));
                    obj.setCh2(rs.getString("ch2"));
                    obj.setCh3(rs.getString("ch3"));
                    obj.setCh4(rs.getString("ch4"));
                    obj.setCh5(rs.getString("ch5"));
                    obj.setCh1_cn(rs.getInt("ch1_cn"));
                    obj.setCh2_cn(rs.getInt("ch2_cn"));
                    obj.setCh3_cn(rs.getInt("ch3_cn"));
                    obj.setCh4_cn(rs.getInt("ch4_cn"));
                    obj.setCh5_cn(rs.getInt("ch5_cn"));
                    obj.setTop_idx(rs.getInt("top_idx"));

                    objList.add(obj);
                } while(rs.next());
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return objList;
    }

    public List<Obj_listDTO> getObjlist(int topidx, String view) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Obj_listDTO> objList = null;

        String query = "SELECT * FROM obj_list WHERE top_idx=? and view=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(topidx));
            pstmt.setString(2, view);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                objList = new ArrayList<Obj_listDTO>();

                do {
                    Obj_listDTO obj = new Obj_listDTO();

                    obj.setNumber(rs.getInt("number"));
                    obj.setIdx(rs.getInt("idx"));
                    obj.setSubject(rs.getString("subject"));
                    obj.setKind(rs.getString("kind"));
                    obj.setNum(rs.getString("num"));
                    obj.setView(rs.getString("view"));
                    obj.setCh1(rs.getString("ch1"));
                    obj.setCh2(rs.getString("ch2"));
                    obj.setCh3(rs.getString("ch3"));
                    obj.setCh4(rs.getString("ch4"));
                    obj.setCh5(rs.getString("ch5"));
                    obj.setCh1_cn(rs.getInt("ch1_cn"));
                    obj.setCh2_cn(rs.getInt("ch2_cn"));
                    obj.setCh3_cn(rs.getInt("ch3_cn"));
                    obj.setCh4_cn(rs.getInt("ch4_cn"));
                    obj.setCh5_cn(rs.getInt("ch5_cn"));
                    obj.setTop_idx(rs.getInt("top_idx"));

                    objList.add(obj);
                } while(rs.next());
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return objList;
    }

    public List<String> getChlist(int number, String num) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> obj_chList = null;

        String query = "SELECT * FROM obj_list WHERE number=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(number));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                obj_chList = new ArrayList<String>();

                for(int i=1; i<=Integer.parseInt(num);i++) {
                    String ch = rs.getString("ch"+i);
                    obj_chList.add(ch);
                }
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return obj_chList;
    }

    public List<Integer> getCh_cnlist(int number, String num) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> obj_ch_cnList = null;

        String query = "SELECT * FROM obj_list WHERE number=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(number));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                obj_ch_cnList = new ArrayList<Integer>();

                for(int i=1; i<=Integer.parseInt(num);i++) {
                    int ch_cn = rs.getInt("ch"+i+"_cn");
                    obj_ch_cnList.add(ch_cn);
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return obj_ch_cnList;
    }

    public List<Integer> getObj_Idxlist(int top_idx) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> obj_idxList = null;

        String query = "SELECT number FROM obj_list WHERE top_idx=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(top_idx));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                obj_idxList = new ArrayList<Integer>();
                do {
                    int number = rs.getInt("number");
                    obj_idxList.add(number);
                } while(rs.next());
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return obj_idxList;
    }

    public int getSum(int number, String num) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int sum = 0;

        String query = "SELECT * FROM obj_list WHERE number=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(number));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                for(int i=1; i<=Integer.parseInt(num);i++) {
                    int ch_cn = rs.getInt("ch"+i+"_cn");
                    sum = sum + ch_cn;
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return sum;
    }

    public Obj_listDTO getObj(int number) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Obj_listDTO obj = null;

        String query = "SELECT * FROM obj_list WHERE number=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(number));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                obj = new Obj_listDTO();

                obj.setNumber(rs.getInt("number"));
                obj.setIdx(rs.getInt("idx"));
                obj.setSubject(rs.getString("subject"));
                obj.setKind(rs.getString("kind"));
                obj.setNum(rs.getString("num"));
                obj.setView(rs.getString("view"));
                obj.setCh1(rs.getString("ch1"));
                obj.setCh2(rs.getString("ch2"));
                obj.setCh3(rs.getString("ch3"));
                obj.setCh4(rs.getString("ch4"));
                obj.setCh5(rs.getString("ch5"));
                obj.setCh1_cn(rs.getInt("ch1_cn"));
                obj.setCh2_cn(rs.getInt("ch2_cn"));
                obj.setCh3_cn(rs.getInt("ch3_cn"));
                obj.setCh4_cn(rs.getInt("ch4_cn"));
                obj.setCh5_cn(rs.getInt("ch5_cn"));
                obj.setTop_idx(rs.getInt("top_idx"));
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return obj;
    }





    public void updateCh_cn(String ch_cn, int number) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        int num = 0;

        String query1 = "SELECT " + ch_cn + " FROM obj_list WHERE number=?";
        String query2 = "UPDATE obj_list SET " + ch_cn + "=? WHERE number=?";

        try{
            conn = getConnection();

            pstmt1 = conn.prepareStatement(query1);
            pstmt1.setString(1, Integer.toString(number));

            rs = pstmt1.executeQuery();
            if(rs.next()) {
                num = rs.getInt(ch_cn);
                pstmt2 = conn.prepareStatement(query2);
                pstmt2.setString(1, Integer.toString(num+1));
                pstmt2.setString(2, Integer.toString(number));
                pstmt2.executeUpdate();

            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (pstmt2 != null)
                try { pstmt2.close(); } catch(SQLException ex) {}
            if (pstmt1 != null)
                try { pstmt1.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }
    }



    //subj_answer
    public List<Subj_answerDTO> getAnswerlist(int idx) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Subj_answerDTO> answerList = null;

        String query = "SELECT * FROM subj_answer WHERE idx=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(idx));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                answerList = new ArrayList<Subj_answerDTO>();

                do {
                    Subj_answerDTO answer = new Subj_answerDTO();

                    answer.setIdx(rs.getInt("idx"));
                    answer.setAnswer(rs.getString("answer"));
                    answer.setReg_date(rs.getString("reg_date"));
                    answer.setTop_idx(rs.getInt("top_idx"));

                    answerList.add(answer);
                } while(rs.next());
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return answerList;
    }

    public void insertAnswer(int idx, String answer, int top_idx) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;

        String query = "INSERT INTO subj_answer VALUES(?, ?, sysdate(), ?)";

        try{
            conn = getConnection();

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(idx));
            pstmt.setString(2, answer);
            pstmt.setString(3, Integer.toString(top_idx));

            pstmt.executeUpdate();

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }
    }

    //subj_list
    public List<Subj_listDTO> getSubjlist(int num) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Subj_listDTO> subjList = null;

        String query = "SELECT * FROM subj_list WHERE num=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(num));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                subjList = new ArrayList<Subj_listDTO>();

                do {
                    Subj_listDTO subj = new Subj_listDTO();

                    subj.setNum(rs.getInt("num"));
                    subj.setIdx(rs.getInt("idx"));
                    subj.setSubject(rs.getString("subject"));
                    subj.setKind(rs.getString("kind"));
                    subj.setArea(rs.getString("area"));
                    subj.setView(rs.getString("view"));
                    subj.setTop_idx(rs.getInt("top_idx"));

                    subjList.add(subj);
                } while(rs.next());
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return subjList;
    }

    public List<Subj_listDTO> getSubjlist(int top_idx, String view) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Subj_listDTO> subjList = null;

        String query = "SELECT * FROM subj_list WHERE top_idx=? and view=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(top_idx));
            pstmt.setString(2, view);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                subjList = new ArrayList<Subj_listDTO>();

                do {
                    Subj_listDTO subj = new Subj_listDTO();

                    subj.setNum(rs.getInt("num"));
                    subj.setIdx(rs.getInt("idx"));
                    subj.setSubject(rs.getString("subject"));
                    subj.setKind(rs.getString("kind"));
                    subj.setArea(rs.getString("area"));
                    subj.setView(rs.getString("view"));
                    subj.setTop_idx(rs.getInt("top_idx"));

                    subjList.add(subj);
                } while(rs.next());
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return subjList;
    }

    public Subj_listDTO getSubj(int num) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Subj_listDTO subj = null;

        String query = "SELECT * FROM subj_list WHERE num=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(num));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                subj = new Subj_listDTO();

                subj.setNum(rs.getInt("num"));
                subj.setIdx(rs.getInt("idx"));
                subj.setSubject(rs.getString("subject"));
                subj.setKind(rs.getString("kind"));
                subj.setArea(rs.getString("area"));
                subj.setView(rs.getString("view"));
                subj.setTop_idx(rs.getInt("top_idx"));
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return subj;
    }

    public List<Integer> getSubj_Idxlist(int top_idx) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> subj_idxList = null;

        String query = "SELECT num FROM subj_list WHERE top_idx=?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(top_idx));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                subj_idxList = new ArrayList<Integer>();
                do {
                    int num = rs.getInt("num");
                    subj_idxList.add(num);
                } while(rs.next());
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return subj_idxList;
    }


    public int answer_Check(String stdno, int top_idx) throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "select * from survey_answer where stdno = ? and top_idx = ?";

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, stdno);
            pstmt.setString(2, Integer.toString(top_idx));
            rs = pstmt.executeQuery();

            if(rs.next()){
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }
        return -1;
    }



    //topsub
    public List<TopsubDTO> getTopsublist() throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<TopsubDTO> topsubList = null;

        String query = "SELECT * FROM topsub where str_to_date(start, '%Y-%m-%d') <= curdate() and str_to_date(end, '%Y-%m-%d') >= curdate()";


        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();


        if(rs.next()) {
            topsubList = new ArrayList<TopsubDTO>();
            do {
                TopsubDTO topsub = new TopsubDTO();

                topsub.setNum(rs.getInt("num"));
                topsub.setTop_idx(rs.getInt("top_idx"));
                topsub.setTopsubject(rs.getString("topsubject"));
                topsub.setStart(rs.getString("start"));
                topsub.setEnd(rs.getString("end"));
                topsub.setInfo(rs.getString("info"));

                topsubList.add(topsub);
            } while(rs.next());
        }

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return topsubList;
    }

    public TopsubDTO getTopsub(int top_idx) throws Exception {

        Connection conn =  null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TopsubDTO topsub = null;

        String query = "SELECT * FROM topsub WHERE top_idx=? and str_to_date(start, '%Y-%m-%d') <= curdate() and str_to_date(end, '%Y-%m-%d') >= curdate()";


        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Integer.toString(top_idx));
            rs = pstmt.executeQuery();

            if(rs.next()) {
                topsub = new TopsubDTO();

                topsub.setNum(rs.getInt("num"));
                topsub.setTop_idx(rs.getInt("top_idx"));
                topsub.setTopsubject(rs.getString("topsubject"));
                topsub.setStart(rs.getString("start"));
                topsub.setEnd(rs.getString("end"));
                topsub.setInfo(rs.getString("info"));

            }
        }catch(Exception ex) {
            System.out.println("1111"+ ex.getMessage());
            ex.printStackTrace();
        }finally{
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }

        return topsub;
    }

    public int userCheck(String stdno, String name) //사용자 인증처리
            throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        String dbname="";
        int x=-1;

        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(
                    "select name from survey_member where stdno = ?");
            pstmt.setString(1, stdno);
            rs= pstmt.executeQuery();

            if(rs.next()){
                dbname= rs.getString("name");
                if(dbname.equals(name))
                    x= 1; //인증 성공
                else
                    x= 0; //비밀번호 틀림
            }else
                x= -1;//해당 아이디 없음
        }catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null)
                try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null)
                try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null)
                try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }

    public void answerCheck(String stdno, int top_idx) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "insert into survey_answer values(?,?)";
        try {
            conn = getConnection();
            assert conn != null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,stdno);
            pstmt.setInt(2,top_idx);
            pstmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(conn != null) conn.close();
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
