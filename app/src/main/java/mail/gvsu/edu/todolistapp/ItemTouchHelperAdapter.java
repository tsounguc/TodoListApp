package mail.gvsu.edu.todolistapp;

public interface ItemTouchHelperAdapter {
    void onItemMoved(int fromPosition, int toPosition);
    void onItemSwiped(int position);
}
