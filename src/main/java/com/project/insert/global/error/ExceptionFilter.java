package com.project.insert.global.error;

import com.project.insert.global.error.Exception.ErrorCode;
import com.project.insert.global.error.Exception.InsertException;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        } catch(InsertException e){
            writeErrorCode(response,e.getErrorCode());
        }catch(Exception e){
            e.printStackTrace();
            writeErrorCode(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void writeErrorCode(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage()
        );

        response.setStatus(errorResponse.getStatus());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(errorResponse.toString());
    }
}
