package com.mannanlive.util;

import com.mannanlive.entity.BorrowEntity;
import com.mannanlive.entity.ConsoleEntity;
import com.mannanlive.entity.GameEntity;
import com.mannanlive.entity.LibraryEntity;
import com.mannanlive.entity.RoleEntity;
import com.mannanlive.entity.UserEntity;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.Target;
import org.junit.Test;

public class SchemaTranslator {
    @Test
    public void testDbSchema() {
        MetadataSources metadata = new MetadataSources(new StandardServiceRegistryBuilder()
                        .applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect").build());

        metadata.addAnnotatedClass(ConsoleEntity.class);
        metadata.addAnnotatedClass(GameEntity.class);
        metadata.addAnnotatedClass(UserEntity.class);
        metadata.addAnnotatedClass(RoleEntity.class);
        metadata.addAnnotatedClass(LibraryEntity.class);
        metadata.addAnnotatedClass(BorrowEntity.class);

        SchemaExport export = new SchemaExport((MetadataImplementor) metadata.buildMetadata());
        export.create(Target.SCRIPT);
    }

}
