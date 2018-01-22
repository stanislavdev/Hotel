package model.util;

import javax.servlet.http.HttpServletRequest;

import static model.util.Constants.PAGE_ATTRIBUTE;

public interface Pagination {
    int NUMBER_OF_ENTRIES_FOR_ONE_PAGE = 4;

    static int getPageId(HttpServletRequest request) {
        int pageId;

        String sPage = request.getParameter(PAGE_ATTRIBUTE);
        if (sPage != null) {
            request.getSession().setAttribute(PAGE_ATTRIBUTE, sPage);
            pageId = Integer.parseInt(sPage);
        } else {
            request.getSession().setAttribute(PAGE_ATTRIBUTE, 1);
            pageId = 1;
        }
        if (pageId != 1) {
            pageId = (pageId - 1) * NUMBER_OF_ENTRIES_FOR_ONE_PAGE + 1;
        }
        return pageId;
    }
}
