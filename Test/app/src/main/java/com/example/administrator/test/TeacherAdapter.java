/*
package com.example.administrator.test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

*/
/**
 * Created by Administrator on 2018/3/12.
 *//*


public class TeacherAdapter extends ArrayAdapter{
    public TeacherAdapter(Context context, int resource, List<Teacher> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 获取老师的数据
        final Teacher teacher = (Teacher) getItem(position);

        // 创建布局
        View oneTeacherView = LayoutInflater.from(getContext()).inflate(R.layout.teacher_item, parent, false);

        // 获取ImageView和TextView
        ImageView imageView = (ImageView) oneTeacherView.findViewById(R.id.teacher_small_imageView);
        TextView textView = (TextView) oneTeacherView.findViewById(R.id.teacher_name_textView);

        // 根据老师数据设置ImageView和TextView的展现
        imageView.setImageResource(teacher.getImageId());
        textView.setText(teacher.getName());
        oneTeacherView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TeacherDetailActivity.class);

                intent.putExtra("teacher_image", teacher.getImageId());
                intent.putExtra("teacher_desc", teacher.getDesc());

                getContext().startActivity(intent);
            }
        });
        return oneTeacherView;
    }
}
*/
