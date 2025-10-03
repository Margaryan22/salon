package Frolov_back.NAILS_WEB_APP.repository;

import org.springframework.data.jpa.repository.JpaRepository;

// Обобщенный репозиторий (ваш вариант)
// Можно оставить так, если уверены, что все ID будут Long
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
    // Здесь можно добавить общие методы, если нужно
}
