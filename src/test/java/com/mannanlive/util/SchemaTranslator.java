package com.mannanlive.util;

import com.mannanlive.entity.GameEntity;
import com.mannanlive.entity.UserEntity;
import com.mannanlive.entity.UserGameEntity;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.Target;

public class SchemaTranslator {

    public static void main(String[] args) {
        MetadataSources metadata = new MetadataSources(new StandardServiceRegistryBuilder()
                        .applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect").build());

        metadata.addAnnotatedClass(GameEntity.class);
        metadata.addAnnotatedClass(UserEntity.class);
        metadata.addAnnotatedClass(UserGameEntity.class);

        SchemaExport export = new SchemaExport((MetadataImplementor) metadata.buildMetadata());
        export.create(Target.SCRIPT);
    }

}
