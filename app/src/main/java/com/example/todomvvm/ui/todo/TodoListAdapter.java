package com.example.todomvvm.ui.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomvvm.R;
import com.example.todomvvm.database.Todo;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoViewHolder> {

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private final TextView todoItemView;
        private TextView description;
        private TextView date;


        private TodoViewHolder(View itemView) {
            super(itemView);
            todoItemView = itemView.findViewById(R.id.textView);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
        }


    }

    private final LayoutInflater mInflater;
    public List<Todo> mTodos; // Cached copy of todos

    TodoListAdapter(TodoFragment context) {
        mInflater = LayoutInflater.from(context.getContext());
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TodoViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        if (mTodos != null) {
            final Todo current = mTodos.get(position);
            holder.todoItemView.setText(current.getTitle());
            holder.description.setText(current.getDetail());
            holder.date.setText(current.getDate());

            holder.todoItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                    UpdateFragment  updateFragment = new UpdateFragment();
                    Bundle args = new Bundle();
                    args.putString("task_title", current.getTitle());
                    args.putString("task_description", current.getDetail());
                    args.putString("created_at" , current.getDate());
                    args.putInt("task_id" , current.getId());
                    updateFragment.setArguments(args);
                    fragmentTransaction.replace(R.id.container, updateFragment);
                    fragmentTransaction.commit();
                   // activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,updateFragment).addToBackStack(null).commit();
                }
            });
        }
        else {
            // Covers the case of data not being ready yet.
            holder.todoItemView.setText(R.string.no_todo);
        }

    }

    /*public interface onTaskListner{
        void onUpdate(int position);
    }*/


    public List<Todo> getTasks() {
        return mTodos;
    }
    void setTodos(List<Todo> todos){
        mTodos = todos;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mTodos has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTodos != null)
            return mTodos.size();
        else return 0;
    }
}