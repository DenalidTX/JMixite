package org.hexworks.jmixite.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestUtils
{
        public static <T> boolean contentsEqual(Collection<T> first, Collection<T> second)
        {
            if (first.size() != second.size()) return false;

            List<T> foundItems = new ArrayList<>();
            for (T item : first)
            {
                for (T otherItem : second)
                {
                    if(item.equals(otherItem))
                    {
                        foundItems.add(item);
                    }
                }
            }

            return foundItems.size() == first.size();
        }
}
