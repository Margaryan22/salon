package Frolov_back.NAILS_WEB_APP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

// Обобщенный репозиторий (ваш вариант)
// Можно оставить так, если уверены, что все ID будут Long
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
    // Здесь можно добавить общие методы, если нужно
}
