package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;

import static java.nio.charset.StandardCharsets.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class FileMemberRepository implements MemberRepository {


    //private List<Member> members = new ArrayList<>();
    private static final String FILE_PATH = "temp/members-txt.dat";
    private static final String DELIMITER = ",";

    @Override
    public void add(Member member)  {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, UTF_8, true))){
            bw.write(member.getId() + DELIMITER + member.getName() + DELIMITER + member.getAge());
            bw.newLine();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH, UTF_8))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] memberDatas = line.split(DELIMITER);
                Member member = new Member(memberDatas[0], memberDatas[1], Integer.valueOf(memberDatas[2]));
                members.add(member);
            }
            return members;
        } catch (FileNotFoundException e){
            return new ArrayList<>();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
