package ib.database.constant;

/**
 * Created by chen on 11/4/16.
 */


/**
 * SQL commands
 * Including select/delete/update/insert
 */
public abstract class SQLCommand
{

    //list all data in books table
    public static String QUERY_1 = "SELECT POST.post_id as _id, st_first_name, user_last_name, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id";
    //List the call numbers of books with the title ‘Database Management’
    public static String QUERY_2 = "INSERT INTO WISHLIST USER.user_id as _id, post_id FROM POST, USER WHERE USER.user_id==POST.user_id";
    //select lbcallnum from LibBook where lbtitle like '%Database Management%'
    //list duedate and returned date
    public static String QUERY_category_spinner1 = "SELECT stock_id as _id, industry_type FROM master_stock";
    public static String QUERY_category_spinner_11 = "SELECT POST.post_id as _id, st_first_name,user_last_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Study Books'";
    public static String QUERY_category_spinner_21 = "SELECT POST.post_id as _id, st_first_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Smart Phone'";
    public static String QUERY_category_spinner_31 = "SELECT POST.post_id as _id, st_first_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Car'";
    public static String QUERY_category_spinner_41 = "SELECT POST.post_id as _id, st_first_name, post_title, post_desc FROM CATEGORY, POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=CATEGORY.cat_id AND cat_name='Furniture'";

    public static String QUERY_display_items = "SELECT item_name, item_desc, item_price, item_id FROM ITEM, POST WHERE ITEM.post_id=POST.post_id AND POST.post_id=?";
    public static String QUERY_3 ="";
    //query all students
    public static String QUERY_4 = "select stid, stname from USER";
    //list books with callnum = 2
    public static String QUERY_5 = "select * from LibBook where lbcallnum = 'c2'";
    //list all students who have checked out books
    public static String QUERY_6 = "select USER.stid, CheckOut.lbcallnum, stname from USER, CheckOut where USER.stid=CheckOut.stid";
    //list USER id and callnum where book is not returned
    public static String QUERY_7 = "select stid, lbcallnum from CheckOut where coreturned='N'";



    public static String RETURN_BOOK = "update checkout set coreturned=? where stid=? and lbcallnum=?";
    public static String CHECK_BOOK = "insert into checkout(stid,lbcallnum,coduedate,coreturned) values(?,?,?,?)";

    //checkout summary
    public static String CHECKOUT_SUMMARY = "select strftime('%m',coduedate) as month,count(*) as total from checkout where strftime('%Y',coduedate)='2011' group by month order by total desc";

    public static String CHECKOUT_LIST = "select checkout.stid as _id, lbtitle, coduedate,coreturned,cofine,stname from checkout,USER,libbook where USER.stid=checkout.stid and libbook.lbcallnum=checkout.lbcallnum";
    public static String postCount = "select count(post_id) from post";
    public static String postInsert = "INSERT INTO POST(post_id,post_title,user_id,post_desc,post_hit_counter) VALUES (?,?,?,?,?)";
    public static String itemInsert = "INSERT INTO ITEM VALUES (?,?,?,?,?,?,?,?)";
    public static String postUpdater = "update post set post_hit_counter=? where post_id=?";

    public static String hotbuy = "SELECT POST.post_id as _id, st_first_name, user_last_name, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id order by post_hit_counter desc" ;

    public static String newbuy = "SELECT POST.post_id as _id, st_first_name, user_last_name, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id order by post_hit_counter desc" ;

    public static String profname= "SELECT USER.user_id as _id, st_first_name, user_last_name,st_email,st_phone FROM USER WHERE user_id=?";
    public static String profupdate= "UPDATE USER SET st_first_name=?, user_last_name=?,st_email=?,st_pass=?,st_phone=? WHERE user_id=?";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public static String logincheck = "SELECT bp_id,bp_email,bp_pass FROM business_partner";

    //ShowBuyListActivity Queries
    public static String showbuylist = "SELECT stock_id as _id, stock_name, stock_price, industry_type, related_market, stock_description FROM master_stock";

    public static String QUERY_market_spinner = "SELECT  stock_id as _id, related_market FROM master_stock group by related_market";
    public static String QUERY_category_spinner_1 = "SELECT stockt_id as _id,  stock_name, stock_price FROM master_stock WHERE related_market='NASDAQ'";
    public static String QUERY_category_spinner_2 = "SELECT stockt_id as _id,  stock_name, stock_price FROM master_stock WHERE related_market='NYSE'";
    public static String QUERY_category_spinner_3 = "SELECT POST.post_id as _id, user_first_name, user_last_name, post_title, post_desc FROM POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=302";
    public static String QUERY_category_spinner_4 = "SELECT POST.post_id as _id, user_first_name, user_last_name, post_title, post_desc FROM POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=303";
    public static String QUERY_category_spinner_5 = "SELECT POST.post_id as _id, user_first_name, user_last_name, post_title, post_desc FROM POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND ITEM.cat_id=304";

    public static String query_spinner = "SELECT stock_id as _id, stock_name, stock_price, industry_type, related_market, stock_description FROM master_stock WHERE related_market=?";

    public static String gethitcount = "SELECT post_hit_counter FROM POST WHERE post_id=?";
    public static String updatehitcount = "UPDATE POST SET post_hit_counter=? WHERE post_id=?";
    //ShowSellList Queries
    public static String showselllist = "select master_stock.stock_id as _id, stock_name, stock_price, stk_quantity, industry_type, related_market, stock_description from stock_account,master_stock,trade,trade_detail,account,business_partner where stock_account.stk_acc_id=trade.stk_acc_id and trade.trade_id=trade_detail.trade_id and trade_detail.stock_id=master_stock.stock_id and stock_account.acc_id=account.acc_id and account.bp_id=business_partner.bp_id and business_partner.bp_id=?";
    public static String sellquery_spinner = "select master_stock.stock_id as _id, stock_name, stock_price, stk_quantity, industry_type, related_market, stock_description from stock_account,master_stock,trade,trade_detail,account,business_partner where stock_account.stk_acc_id=trade.stk_acc_id and trade.trade_id=trade_detail.trade_id and trade_detail.stock_id=master_stock.stock_id and stock_account.acc_id=account.acc_id and account.bp_id=business_partner.bp_id and business_partner.bp_id=? and related_market=?";
    public static String callMgr = "Select m_id as _id, m_phone FROM manager where bp_id=?";

    //ShowStockDetail Queries
    public static String buy = "UPDATE stock_account SET stk_quantity=? WHERE stk_name=? AND acc_id=?";

    public static String sell = "UPDATE stock_account SET stk_quantity=? WHERE stk_name=? AND acc_id=?";

    //History Queries
    public static String history = "SELECT trade.trade_id AS _id, trade_date, trade_type, trade_stk_quantity, stock_name FROM trade, trade_detail, master_stock WHERE trade.trade_id=trade_detail.trade_id AND trade_detail.stock_id=master_stock.stock_id";

    //HotDeals Queries
    public static String showhotbuylist = "SELECT POST.post_id as _id, user_first_name, user_last_name, user_phone, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id AND USER.user_id!=? ORDER BY post_hit_counter desc";
    public static String query_spinner_hot = "SELECT POST.post_id as _id, user_first_name, user_last_name, user_phone, post_title, post_desc FROM POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND USER.user_id!=? AND ITEM.cat_id=? ORDER BY post_hit_counter desc";

    //NewDeals Queries
    public static String shownewbuylist = "SELECT master_stock.stock_id as _id, stock_name, stock_price FROM master_stock" ;
    public static String query_spinner_new = "SELECT POST.post_id as _id, user_first_name, user_last_name, user_phone, post_title, post_desc FROM POST, USER, ITEM WHERE USER.user_id==POST.user_id AND POST.post_id=ITEM.post_id AND USER.user_id!=? AND ITEM.cat_id=? ORDER BY POST.post_id desc";


    //ProfilePage Queries
    public static String showprofile = "SELECT bp_first_name, bp_last_name,bp_pass,bp_email, bp_birthdate FROM business_partner WHERE bp_id=?";
    public static String updateprofile = "UPDATE business_partner SET bp_first_name=?, bp_last_name=?,bp_email=?,bp_pass=?, bp_birthdate=? WHERE bp_id=?";

    //Favorite Queries
    public static String getwishlist = "SELECT wd_id, stock_id FROM WISHLISTDETAILS WHERE bp_id=?";
    public static String showwishlist = "SELECT master_stock.stock_id AS _id, stock_name, stock_price FROM master_stock,wishlistdetails where master_stock.stock_id=wishlistdetails.stock_id and wishlistdetails.bp_id=?";

    public static String getposterdetails = "SELECT master_stock.stock_id AS _id, stock_name, stock_price, industry_type, related_market, stock_description FROM master_stock ";

    //MajorList Queries
    public static String getpostdetails1 = "SELECT post_title, post_desc FROM POST";
    public static String getposttitle = "SELECT post_title, post_desc FROM POST WHERE post_id=?";
    public static String getitemdetails = "SELECT ITEM.item_id AS _id, item_name, item_desc, item_price FROM ITEM, POST WHERE ITEM.post_id=POST.post_id AND POST.post_id=?";
    //public static String getwishid = "SELECT wd_id FROM WISHLISTDETAILS where user_id=?";
    public static String getwdid = "SELECT wd_id FROM WISHLISTDETAILS ORDER BY wd_id desc";
    public static String addtowishlist = "INSERT INTO WISHLISTDETAILS (wd_id,user_id,item_id) VALUES (?,?,?)";

    //AddNewItem
    public static String getpostid = "SELECT post_id FROM POST ORDER BY post_id desc";
    public static String getitemid = "SELECT item_id FROM ITEM ORDER BY item_id desc";
    public static String insertpost = "INSERT INTO POST(post_id,post_title,post_desc,post_hit_counter,user_id) VALUES (?,?,?,?,?)";
    public static String insertitem = "INSERT INTO ITEM(item_id,post_id,cat_id,item_name,item_qoh,item_price,item_desc) VALUES (?,?,?,?,?,?,?)";

    //showstockdetail
    public static String getpostdetails = "SELECT POST.post_id AS _id, post_title, post_desc FROM POST where post_id = ?";
    public static String getuserid = "SELECT user_id FROM POST where post_id = ?";
    public static String deleteitem = "DELETE FROM ITEM where item_id = ?";


    //UpdateItemDetails
    public static String getitemdetails_updatePage = "SELECT item_name, item_qoh, item_price, item_desc, cat_id FROM ITEM WHERE ITEM.item_id=?";
    public static String updateItemDetails = "UPDATE ITEM set item_name=?, item_qoh=?, item_price=?, item_desc=?, cat_id=? where item_id=?";

    //MainActivity
    public static String recent_trades = "SELECT trade.trade_id AS _id, stk_name,trade_date from stock_account,trade where stock_account.stk_acc_id=trade.stk_acc_id order by trade_date desc;";
    public static String getname = "SELECT bp_first_name FROM business_partner WHERE bp_id=?";


    //EventRegistration
    public static String adminflag = "SELECT user_admin_flag FROM USER WHERE user_id = ?";
    public static String getedid = "SELECT ed_id FROM EVENTDETAILS ORDER BY ed_id desc";
    public static String eventregisteration = "INSERT INTO EVENTDETAILS VALUES(?,?,?)";
    public static String eventunregisteration = "DELETE FROM EVENTDETAILS where event_id = ? AND user_id=?";
    public static String getusercount = "SELECT COUNT(*) FROM USER";
    public static String getrsvpcount = "SELECT COUNT(*) FROM EVENTDETAILS WHERE event_id=?";
    public static String rsvpcheck = "SELECT COUNT(*) FROM EVENTDETAILS WHERE event_id=? AND user_id=?";


    //UpdateEventDetails
    public static String updateEventDetails = "UPDATE EVENT SET event_title=?,event_desc=? WHERE event_id=?";

    //History
    public static String mypostlist = "SELECT POST.post_id as _id, user_first_name, user_last_name, user_phone, post_title, post_desc FROM POST, USER WHERE USER.user_id==POST.user_id AND USER.user_id=?";
    public static String MASTER_STOCK="select mstk_id,mstkname,mstkprice from MASTER_STOCK";


}