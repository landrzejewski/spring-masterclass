package pl.training.shop.common;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.javamoney.moneta.FastMoney;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class FastMoneyUserType implements CompositeUserType {

    public String[] getPropertyNames() {
        return new String[] {"currency", "amount"};
    }

    public Type[] getPropertyTypes() {
        return new Type[]{StringType.INSTANCE, DoubleType.INSTANCE};
    }

    @Override
    public Class<FastMoney> returnedClass() {
        return FastMoney.class;
    }

    public Object getPropertyValue(Object component, int propertyIndex) {
        if (component == null) {
            return null;
        }
        var money = (FastMoney) component;
        switch (propertyIndex) {
            case 0:
                return money.getCurrency().getCurrencyCode();
            case 1:
                return money.getNumber().numberValue(Double.class);
            default:
                throw new HibernateException("Invalid property index [" + propertyIndex + "]");
        }
    }

    public void setPropertyValue(Object component, int propertyIndex, Object value) {
        if (component == null) {
            return;
        }
        throw new HibernateException("Called setPropertyValue on an immutable type {" + component.getClass() + "}");
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor session, Object object) throws SQLException, SQLException {
        assert names.length == 2;
        FastMoney money = null;
        var currency = resultSet.getString(names[0]);
        if (!resultSet.wasNull()) {
            var amount = resultSet.getDouble(names[1]);
            money = FastMoney.of(amount, currency);
        }
        return money;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int property, SharedSessionContractImplementor session) throws SQLException {
        if (null == value) {
            preparedStatement.setNull(property, StringType.INSTANCE.sqlType());
            preparedStatement.setNull(property + 1, DoubleType.INSTANCE.sqlType());
        } else {
            FastMoney amount = (FastMoney) value;
            preparedStatement.setString(property, amount.getCurrency().getCurrencyCode());
            preparedStatement.setDouble(property + 1, amount.getNumber().numberValue(Double.class));
        }
    }

    @Override
    public boolean equals(Object o1, Object o2) {
        return Objects.equals(o1, o2);
    }

    @Override
    public int hashCode(Object value) {
        return value.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object deepCopy(Object value) {
        return value;
    }

    @Override
    public Serializable disassemble(Object value, SharedSessionContractImplementor paramSessionImplementor) {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, SharedSessionContractImplementor sessionImplementor, Object owner) {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, SharedSessionContractImplementor paramSessionImplementor, Object owner) {
        return original;
    }

}