package com.inspur.service.impl;

import com.inspur.entity.Book;
import com.inspur.entity.Borrow;
import com.inspur.repository.BookRepository;
import com.inspur.repository.BorrowRepository;
import com.inspur.repository.impl.BookerRepositoryImpl;
import com.inspur.repository.impl.BorrowRepositoryImpl;
import com.inspur.service.BookService;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository = new BookerRepositoryImpl();
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();
    private final int LIMIT = 6;

    @Override
    public List<Book> findAll(int page) {
        int index = (page - 1) * LIMIT;
        return bookRepository.findAll(index,LIMIT);
    }

    @Override
    public int getPages() {
        int count = bookRepository.count();
        if (count % LIMIT == 0){
            return count/LIMIT;
        }else {
            return count/LIMIT + 1;
        }
    }

    @Override
    public void addBrrow(Integer bookid, Integer readerid) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime = simpleDateFormat.format(date);
        System.out.println(borrowTime);
        // 还书时间 借书时间 加上 14 天
        Calendar calendar = Calendar.getInstance();
        int dates = calendar.get(Calendar.DAY_OF_YEAR) + 14;  // 获取 今天 事一天中的第几天 并且加十四天
        System.out.println(dates);
        calendar.set(Calendar.DAY_OF_YEAR, dates);  // 借书时间 set 进去
        String returnTime = simpleDateFormat.format(calendar.getTime()); // 得到还书时间
        System.out.println(returnTime);
        borrowRepository.insert(bookid,readerid,borrowTime,returnTime,null,0);
    }

    @Override
    public List<Borrow> findAllBorrowByReaderId(Integer id, Integer page) {
        int index = (page-1)*LIMIT;
        return borrowRepository.findAllByReaderId(id,index,LIMIT);
    }

    @Override
    public int getBorrowPages(Integer readerid) {
        int count = borrowRepository.count(readerid);
        int page = 0;
        if(count % LIMIT == 0){
            page = count/LIMIT;
        }else{
            page = count/LIMIT+1;
        }
        return page;
    }

    @Override
    public List<Borrow> findAllBorrowByState(Integer state, Integer page) {
        int index = (page-1)*LIMIT;
        return borrowRepository.findAllByState(state,index,LIMIT);
    }

    @Override
    public int getBorrowPagesByState(Integer state) {
        int count = borrowRepository.countByState(state);
        int page = 0;
        if(count % LIMIT == 0){
            page = count/LIMIT;
        }else{
            page = count/LIMIT+1;
        }
        return page;
    }

    @Override
    public void handleBorrow(Integer borrowId, Integer state, Integer adminId) {
        borrowRepository.handle(borrowId,state,adminId);
    }

    public static void main(String[] args) {
        BookService bookService = new BookServiceImpl();
        bookService.addBrrow(1,2);
    }
}
